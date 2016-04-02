@TypeDefs({
		@TypeDef(
				name = "encryptedString",
				typeClass = EncryptedStringType.class,
				parameters = {
						@Parameter(name = "encryptorRegisteredName", value = "defaultStringEncryptor")
				}
		)
})package com.example.spring.model.user;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedStringType;
