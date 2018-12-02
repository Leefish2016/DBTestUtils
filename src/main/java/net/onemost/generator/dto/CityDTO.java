package net.onemost.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * @author onemost
 */
@Data
public class CityDTO {

    private String code;

    private String name;

    private List<CityDTO> children;

}
