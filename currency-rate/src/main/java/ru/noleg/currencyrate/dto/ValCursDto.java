package ru.noleg.currencyrate.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public record ValCursDto(
        @JacksonXmlProperty(isAttribute = true, localName = "Date")
        String date,

        @JacksonXmlProperty(isAttribute = true, localName = "name")
        String name,

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Valute")
        List<ValuteDto> valutes
) {
}
