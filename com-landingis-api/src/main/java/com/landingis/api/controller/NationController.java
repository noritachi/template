package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.nation.NationDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.nation.CreateNationForm;
import com.landingis.api.mapper.NationMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.NationCriteria;
import com.landingis.api.storage.model.Nation;
import com.landingis.api.storage.repository.NationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/nation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class NationController extends ABasicController{

    @Autowired
    NationRepository nationRepository;

    @Autowired
    NationMapper nationMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<NationDto>> list(NationCriteria nationCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.NATION_ERROR_UNAUTHORIZED, "Not allowed get list.");
        }
        ApiMessageDto<ResponseListObj<NationDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Nation> listNation = nationRepository.findAll(nationCriteria.getSpecification(), pageable);
        ResponseListObj<NationDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(nationMapper.fromEntityListToNationDtoList(listNation.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listNation.getTotalPages());
        responseListObj.setTotalElements(listNation.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<NationDto>> autoComplete(NationCriteria nationCriteria) {
        ApiMessageDto<ResponseListObj<NationDto>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Nation> listNation = nationRepository.findAll(nationCriteria.getSpecification(), Pageable.unpaged());
        ResponseListObj<NationDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(nationMapper.fromEntityListToNationDtoAutoComplete(listNation.getContent()));

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<NationDto> get(@PathVariable("id") Long id) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.NATION_ERROR_UNAUTHORIZED, "Not allowed get.");
        }
        ApiMessageDto<NationDto> result = new ApiMessageDto<>();

        Nation nation = nationRepository.findById(id).orElse(null);

        if(nation == null) {
            throw new RequestException(ErrorCode.NATION_ERROR_NOT_FOUND, "Not found nation.");
        }
        result.setData(nationMapper.fromEntityToAdminDto(nation));
        result.setMessage("Get nation success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateNationForm createNationForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.NATION_ERROR_UNAUTHORIZED, "Not allowed to create.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Nation nation = nationMapper.fromCreateNationFormToEntity(createNationForm);
        if(createNationForm.getParentId() != null) {
            Nation parentNation = nationRepository.findById(createNationForm.getParentId()).orElse(null);
            if(parentNation == null || parentNation.getParentNation() != null) {
                throw new RequestException(ErrorCode.NATION_ERROR_NOT_FOUND, "Not found nation parent");
            }
            nation.setParentNation(parentNation);
        }
        nationRepository.save(nation);
        apiMessageDto.setMessage("Create nation success");
        return apiMessageDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateNationForm updateNationForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.NATION_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Nation nation = nationRepository.findById(updateNationForm.getId()).orElse(null);
        if(nation == null) {
            throw new RequestException(ErrorCode.NATION_ERROR_NOT_FOUND, "Not found nation.");
        }
        if(nation.getStatus().equals(updateNationForm.getStatus()) && nation.getParentNation() == null) {
            nation.getNationList().forEach(child -> child.setStatus(updateNationForm.getStatus()));
            nationRepository.saveAll(nation.getNationList());
        }
        if(StringUtils.isNoneBlank(nation.getImage()) && !updateNationForm.getNationImage().equals(nation.getImage())) {
            landingIsApiService.deleteFile(nation.getImage());
        }
        nationMapper.fromUpdateNationFormToEntity(updateNationForm, nation);
        nationRepository.save(nation);
        apiMessageDto.setMessage("Update nation success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<NationDto> delete(@PathVariable("id") Long id) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.NATION_ERROR_NOT_FOUND, "Not allowed to delete.");
        }
        ApiMessageDto<NationDto> result = new ApiMessageDto<>();

        Nation nation = nationRepository.findById(id).orElse(null);
        if(nation == null) {
            throw new RequestException(ErrorCode.NATION_ERROR_NOT_FOUND, "Not found nation");
        }
        landingIsApiService.deleteFile(nation.getImage());
        nationRepository.delete(nation);
        result.setMessage("Delete nation success");
        return result;
    }
}
