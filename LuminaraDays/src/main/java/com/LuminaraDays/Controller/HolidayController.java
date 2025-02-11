package com.LuminaraDays.Controller;

import com.LuminaraDays.Controller.Dto.HolidayDTO;
import com.LuminaraDays.Service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@CrossOrigin("*")
@Tag(name = "Holiday API", description = "Endpoints for retrieving holiday information")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Operation(summary = "Get all holidays")
    @GetMapping
    public List<HolidayDTO> getHolidays() {
        return holidayService.getHolidays();
    }

    @Operation(summary = "Search holidays by date")
    @GetMapping("/search/date")
    public List<HolidayDTO> searchByDate(@RequestParam String date) {
        return holidayService.searchByDate(date);
    }

    @Operation(summary = "Search holidays by name")
    @GetMapping("/search/name")
    public List<HolidayDTO> searchByName(@RequestParam String name) {
        return holidayService.searchByName(name);
    }

    @Operation(summary = "Search holidays by type")
    @GetMapping("/search/type")
    public List<HolidayDTO> searchByType(@RequestParam String type) {
        return holidayService.searchByType(type);
    }
}
