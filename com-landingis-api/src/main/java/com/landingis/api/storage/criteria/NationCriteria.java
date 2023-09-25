package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Nation;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class NationCriteria {
    private Long id;
    private String name;
    private String postCode;
    private Integer kind;
    private Long parentId;


    public Specification<Nation> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Nation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
//                Join<Category, Category> joinCategory = root.join("parentCategory", JoinType.INNER);

                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }

                if(!StringUtils.isEmpty(getName())) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + getName().toLowerCase() + "%"));
                }

                if(!StringUtils.isEmpty(getPostCode())) {
                    predicates.add(cb.like(cb.lower(root.get("postCode")), "%" + getPostCode().toLowerCase() + "%"));
                }

                if(getParentId() != null) {
                    predicates.add(cb.equal(root.get("parentNation"), getParentId()));
                }
                else {
                    predicates.add(cb.isNull(root.get("parentNation")));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
