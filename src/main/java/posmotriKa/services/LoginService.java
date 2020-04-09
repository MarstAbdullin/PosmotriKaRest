package posmotriKa.services;

import posmotriKa.dto.LoginDto;
import posmotriKa.dto.TokenDto;


public interface LoginService {
    TokenDto signIn(LoginDto loginData);
}
