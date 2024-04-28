package com.reservation.productservice.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Data
public class Paging {
    private int page;
    private int limit;
    private String orderBy;
    private String direction;

    public PageRequest getPageRequest(){
        return PageRequest.of(page, limit, Sort.by(Direction.valueOf(direction), orderBy));
    }

}
