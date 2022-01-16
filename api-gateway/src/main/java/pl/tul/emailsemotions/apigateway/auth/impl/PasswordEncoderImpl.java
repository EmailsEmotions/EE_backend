package pl.tul.emailsemotions.apigateway.auth.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("passwordEncoder")
public class PasswordEncoderImpl implements PasswordEncoder {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence charSequence) {
        return PASSWORD_ENCODER.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return PASSWORD_ENCODER.matches(charSequence, s);
    }
}
