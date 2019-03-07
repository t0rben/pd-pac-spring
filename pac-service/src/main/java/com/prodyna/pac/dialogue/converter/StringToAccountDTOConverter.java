package com.prodyna.pac.dialogue.converter;

import com.prodyna.pac.model.dto.AccountDTO;
import com.prodyna.pac.model.dto.AccountOperation;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToAccountDTOConverter implements Converter<String, AccountDTO> {

    @Override
    public AccountDTO convert(String source) {

        final String[] parts = source.split("z");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Input cannot be converted to AccountDTO");
        }

        final AccountOperation accountOperation = AccountOperation.valueOf(parts[0]);

        final Double amount = Double.valueOf(parts[1]);

        final AccountDTO accountDTO1 = new AccountDTO();

        ConfigurablePropertyAccessor accountDTO = PropertyAccessorFactory.forDirectFieldAccess(accountDTO1);
        accountDTO.setPropertyValue("accountOperation", accountOperation);
        accountDTO.setPropertyValue("amount", amount);

        return accountDTO1;
    }
}
