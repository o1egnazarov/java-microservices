package ru.noleg.processing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.noleg.processing.dto.CreateAccountDto;
import ru.noleg.processing.entity.Account;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper extends BaseMapper<Account, CreateAccountDto> {
    @Override
    Account mapToEntity(CreateAccountDto dto);

    @Override
    CreateAccountDto mapToDto(Account entity);

    @Override
    List<Account> mapToEntityList(List<CreateAccountDto> dtoList);

    @Override
    List<CreateAccountDto> mapToDtoList(List<Account> entityList);
}
