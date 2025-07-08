package ru.noleg.processing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.noleg.processing.dto.AccountDto;
import ru.noleg.processing.dto.CreateAccountDto;
import ru.noleg.processing.entity.Account;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper extends BaseMapper<Account, AccountDto> {
    Account mapToCreateEntity(CreateAccountDto dto);

    @Override
    AccountDto mapToDto(Account entity);

    @Override
    List<Account> mapToEntityList(List<AccountDto> dtoList);

    @Override
    List<AccountDto> mapToDtoList(List<Account> entityList);
}
