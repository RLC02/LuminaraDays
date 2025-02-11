package com.LuminaraDays.Domain.Repository;

import com.LuminaraDays.Controller.Dto.HolidayDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository {
    List<HolidayDTO> fetchHolidays(int year);
}

