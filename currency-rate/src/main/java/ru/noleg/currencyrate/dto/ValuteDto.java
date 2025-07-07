package ru.noleg.currencyrate.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

public record ValuteDto(
        @JacksonXmlProperty(isAttribute = true, localName = "ID")
        String id,
        @JacksonXmlProperty(localName = "NumCode")
        int numCode,
        @JacksonXmlProperty(localName = "CharCode")
        String charCode,
        @JacksonXmlProperty(localName = "Nominal")
        int nominal,
        @JacksonXmlProperty(localName = "Name")
        String name,
        @JacksonXmlProperty(localName = "Value")
        String value,
        @JacksonXmlProperty(localName = "VunitRate")
        String vunitRate
) {
}
