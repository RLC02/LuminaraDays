package com.LuminaraDays.Service.Impl;

import com.LuminaraDays.Controller.Dto.HolidayDTO;
import com.LuminaraDays.Domain.Repository.HolidayRepository;
import com.LuminaraDays.Service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService {

    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

    private final HolidayRepository holidayRepository;
    private final Map<Integer, List<HolidayDTO>> holidayCache = new HashMap<>();

    public HolidayServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<HolidayDTO> getHolidays() {
        int currentYear = LocalDate.now().getYear();

        // Retorna do cache se já estiver carregado
        return holidayCache.computeIfAbsent(currentYear, holidayRepository::fetchHolidays);
    }

    @Override
    public List<HolidayDTO> searchByDate(String date) {
        return getHolidays().stream()
                .filter(holiday -> holiday.getDate().contains(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<HolidayDTO> searchByName(String name) {
        return getHolidays().stream()
                .filter(holiday -> holiday.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<HolidayDTO> searchByType(String type) {
        return getHolidays().stream()
                .filter(holiday -> holiday.getType().toLowerCase().contains(type.toLowerCase()))
                .collect(Collectors.toList());
    }
}
