package net.onemost.generator.strategy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.onemost.generator.dto.CityDTO;
import net.onemost.generator.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 生成地址
 * @author onemost
 */
@Slf4j
public class AddressGenerator implements BaseStrategy<String> {

    private static final List<CityDTO> CITY;

    static{
        CITY = new ArrayList<>();
        CITY.addAll(JSON.parseArray(FileUtils.getFileContent("pcas-code.json"), CityDTO.class));
    }

    @Override
    public String generator() {
        StringBuffer stringBuffer = new StringBuffer();
        int random = RANDOM.nextInt(CITY.size());
        var city = CITY.get(random);
        var children = city.getChildren();
        stringBuffer.append(CITY.get(random).getName());
        while(children != null && !children.isEmpty()){
            random  = RANDOM.nextInt(children.size());
            city = children.get(random);
            children = city.getChildren();
            stringBuffer.append(city.getName());
        }
        return stringBuffer.toString();
    }
}
