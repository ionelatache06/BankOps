package com.victoria.restexercise.mapper;

import com.victoria.restexercise.dto.AccountDto;
import com.victoria.restexercise.model.AccountEntity;

public abstract class AccountEntityToAccountDtoMapper {
    public abstract AccountDto mapEntityToDto(AccountEntity accountEntity);
}
