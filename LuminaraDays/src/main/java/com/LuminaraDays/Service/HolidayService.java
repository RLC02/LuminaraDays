package com.LuminaraDays.Service;

import com.LuminaraDays.Controller.Dto.HolidayDTO;
import java.util.List;

public interface HolidayService {
    List<HolidayDTO> getHolidays();
    List<HolidayDTO> searchByDate(String date);
    List<HolidayDTO> searchByName(String name);
    List<HolidayDTO> searchByType(String type);
}
