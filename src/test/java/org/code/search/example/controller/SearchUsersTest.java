package org.code.search.example.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.code.search.example.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchUsersTest {

    List<Users> users = new ArrayList<>();

    private void prepareUsersTestData() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Users user1 = new Users(Long.valueOf(73), "Test User1", cal.getTime(), true);
        users.add(user1);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -2);
        Users user2 = new Users(Long.valueOf(74), "Test User1", cal.getTime(), true);
        users.add(user2);
        Users user3 = new Users(Long.valueOf(75), "Alvarez Black", new Date(), false);
        users.add(user3);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -8);
        Users user4 = new Users(Long.valueOf(76), "Sample User", cal.getTime(), true);
        users.add(user4);
        cal = Calendar.getInstance();
        cal.set(2021, 02, 21, 00, 00, 00);
        cal.set(Calendar.MILLISECOND, 0);
        Users user5 = new Users(Long.valueOf(78), "Charlene Coleman", cal.getTime(), false);
        users.add(user5);
        Users user6 = new Users(Long.valueOf(32), "Charlene Test", new Date(), true);
        users.add(user6);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        Users user7 = new Users(Long.valueOf(44), "Jennifer Gaines", cal.getTime(), false);
        users.add(user7);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -5);
        Users user8 = new Users(Long.valueOf(56), "Burgess England", cal.getTime(), true);
        users.add(user8);
        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        Users user9 = new Users(Long.valueOf(105), "Burgess London", cal.getTime(), true);
        users.add(user9);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Users user10 = new Users(Long.valueOf(354), "Elma Castro", cal.getTime(), true);
        users.add(user10);
    }


    @Test
    public void searchUsersByGivenParam() {
        prepareUsersTestData();
        SearchUsers searchFile = new SearchUsers();
        searchFile.setUsers(users);
        List<Users> matchingUsers = null;
        matchingUsers = searchFile.searchUsersByGivenParam("_id", "56");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(1, matchingUsers.size());

        matchingUsers = searchFile.searchUsersByGivenParam("name", "Elma Castro");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(1, matchingUsers.size());
        Assertions.assertEquals(Long.valueOf(354), matchingUsers.get(0).getId());

        matchingUsers = searchFile.searchUsersByGivenParam("name", "ElmaCastro");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(0, matchingUsers.size());

        matchingUsers = searchFile.searchUsersByGivenParam("verified", "true");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(7, matchingUsers.size());

        matchingUsers = searchFile.searchUsersByGivenParam("verified", "false");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(3, matchingUsers.size());

        matchingUsers = searchFile.searchUsersByGivenParam("created_at", "2021-03-21T00:00:00+11:00");
        Assertions.assertNotNull(matchingUsers);
        Assertions.assertEquals(1, matchingUsers.size());

    }

}