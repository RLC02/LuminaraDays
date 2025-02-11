# LuminaraDays

Datas que brilham no tempo. Api de consulta de feriados e eventos marcantes de qualquer lugar do mundo.

## Diagrama de Classe

```mermaid
classDiagram
    direction TB

    %% ===============================
    %% DTO (Data Transfer Object)
    %% ===============================
    class HolidayDTO {
        - String date
        - String name
        - String type
        + HolidayDTO(String date, String name, String type)
    }

    %% ===============================
    %% Repository (Data Access Layer)
    %% ===============================
    class HolidayRepository {
        + List<HolidayDTO> findAll()
    }

    class HolidayRepositoryImpl {
        + List<HolidayDTO> findAll()
    }

    HolidayRepository <|.. HolidayRepositoryImpl

    %% ===============================
    %% Service Layer
    %% ===============================
    class HolidayService {
        + List<HolidayDTO> getHolidays()
        + List<HolidayDTO> searchByDate(String date)
        + List<HolidayDTO> searchByName(String name)
        + List<HolidayDTO> searchByType(String type)
    }

    class HolidayServiceImpl {
        - List<HolidayDTO> holidayCache
        - int yearCache
        + List<HolidayDTO> getHolidays()
        + List<HolidayDTO> searchByDate(String date)
        + List<HolidayDTO> searchByName(String name)
        + List<HolidayDTO> searchByType(String type)
    }

    HolidayService <|.. HolidayServiceImpl
    HolidayServiceImpl --> HolidayRepository : Uses

    %% ===============================
    %% Controller Layer
    %% ===============================
    class HolidayController {
        - HolidayService holidayService
        + List<HolidayDTO> getHolidays()
        + List<HolidayDTO> searchByDate(String date)
        + List<HolidayDTO> searchByName(String name)
        + List<HolidayDTO> searchByType(String type)
    }

    HolidayController --> HolidayService : Uses
```
