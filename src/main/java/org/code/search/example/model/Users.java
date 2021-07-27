package org.code.search.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bnaragani created on 23/07/2021
 */
public class Users {

    @JsonProperty("_id")
    Long id;

    String name;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    Date createdAt;

    boolean verified;

    @JsonIgnore
    List<Tickets> tickets;

    public Users() {
    }

    public Users(Long id, String name, Date createdAt, boolean verified) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.verified = verified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Users)) {
            return false;
        }
        Users users = (Users) o;
        return isVerified() == users.isVerified() && getId().equals(users.getId()) && getName().equals(users.getName()) && Objects
                .equals(getCreatedAt(), users.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreatedAt(), isVerified());
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("User Details : \n");
        sb.append("    id: ").append(id).append("\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    createdAt: ").append(createdAt).append("\n");
        sb.append("    verified: ").append(verified).append("\n");
        if(tickets != null && !tickets.isEmpty())
            sb.append("    tickets  ").append(tickets.stream().map(tickets1 -> tickets1.getSubject()).collect(Collectors.toList())).append("\n");
        return sb.toString();

    }
}
