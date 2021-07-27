package org.code.search.example.controller;

import java.util.List;
import org.code.search.example.model.Users;

public interface SearchUsersInterface {

    List<Users> searchUsersByGivenParam(String parameter, String value);

}
