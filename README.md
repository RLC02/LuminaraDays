# LuminaraDays
Datas que brilham no tempo. Api de consulta de feriados e eventos marcantes de qualquer lugar do mundo.

classDiagram
    class Holiday {
        +String date
        +String name
        +String type
    }
    class Country {
        +String code
        +String name
    }
    class Region {
        +String name
    }
    Country --> Holiday : "has"
    Region --> Holiday : "has"
