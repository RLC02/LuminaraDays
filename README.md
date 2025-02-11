<p align="center">
  <img src="https://github.com/RLC02/LuminaraDays/blob/main/Logo.png" width="200" height="200" />
</p>

# LuminaraDays

LuminaraDays é uma API que fornece informações sobre feriados de forma eficiente e organizada. Desenvolvida utilizando **Java Spring Boot**, a API permite que usuários consultem feriados nacionais e filtrá-los por data, nome e tipo.

Com um design modular e escalável, o projeto segue boas práticas de desenvolvimento e inclui **Swagger** para documentação interativa.

---

## 🛠️ Tecnologias Utilizadas

### **Backend**
| Tecnologia           | Descrição                          |
|---------------------|------------------------------------|
| <img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=openjdk&logoColor=&color=" alt="Java"/> | Linguagem de programação principal. |
| <img src="https://img.shields.io/badge/spring-%23ED8B00.svg?&style=for-the-badge&logo=spring&logoColor=&color=white" alt="Spring"/> | Framework para aplicações Java.     |
| <img src="https://img.shields.io/badge/-Swagger-%2385EA2D?style=for-the-badge&logo=swagger&logoColor=black"/> | Ferramenta para documentação de APIs. |
| <img src="https://img.shields.io/badge/-HTMLUnit-green?style=for-the-badge"/> | Biblioteca para web scraping. |

### **Fonte para scraping**
| API               | Ícone | Descrição                          | Link |
|-------------------|-------|------------------------------------|------|
| **Time and Date** | 📅   | Fornece informações sobre feriados | [TimeAndDate](https://www.timeanddate.com/holidays/) |

---

## 🚀 Execução

### **Backend** ☕
```bash
mvn spring-boot:run
```
O backend estará disponível na porta **8080** por padrão.

---

## 💡 Funcionalidades
- 📆 Consulta de feriados nacionais
- 🔍 Filtragem por data, nome ou tipo
- 🚀 API documentada com Swagger

---

## Diagrama de classe

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
---

## 📖 Documentação
A documentação da API pode ser acessada via Swagger:
```
http://localhost:8080/swagger-ui/
```

---
**Desenvolvido por [Ricardo](https://github.com/RLC02)** ✨
