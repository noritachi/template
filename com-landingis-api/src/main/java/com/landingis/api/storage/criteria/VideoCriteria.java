package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Article;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.Video;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class VideoCriteria {

    private Long id;
    private String title;
    private Integer kind;

    public Specification<Video> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }

                if(getTitle() != null) {
                    predicates.add(cb.like(root.get("title"), "%" + getTitle().toLowerCase() + "%"  ));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
