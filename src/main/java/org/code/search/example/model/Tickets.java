package org.code.search.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author bnaragani created on 24/07/2021
 */
public class Tickets {

    @JsonProperty("_id")
    String id;

    @JsonProperty("created_at")
    Date createdAt;

    @JsonProperty("type")
    String type;

    @JsonProperty("subject")
    String subject;

    @JsonProperty("assignee_id")
    Long assigneeId;

    @JsonProperty("tags")
    List<String> tags;

    @JsonIgnore
    Users assigneeDetails;

    public Tickets() {
    }

    public Tickets(String id, Date createdAt, String type, String subject, Long assigneeId, List<String> tags) {
        this.id = id;
        this.createdAt = createdAt;
        this.type = type;
        this.subject = subject;
        this.assigneeId = assigneeId;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Users getAssigneeDetails() {
        return assigneeDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tickets)) {
            return false;
        }
        Tickets tickets = (Tickets) o;
        return getId().equals(tickets.getId()) && getCreatedAt().equals(tickets.getCreatedAt()) && getType().equals(tickets.getType())
                && getSubject().equals(tickets.getSubject()) && Objects.equals(getAssigneeId(), tickets.getAssigneeId())
                && Objects.equals(getTags(), tickets.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedAt(), getType(), getSubject(), getAssigneeId(), getTags());
    }

    public void setAssigneeDetails(Users assigneeDetails) {
        this.assigneeDetails = assigneeDetails;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket Details : \n");
        sb.append("    _id          ").append(id).append("\n");
        sb.append("    created_at   ").append(createdAt).append("\n");
        sb.append("    type         ").append(type).append("\n");
        sb.append("    subject      ").append(subject).append("\n");
        if(assigneeId != null)
            sb.append("    assignee_id  ").append(assigneeId).append("\n");
        sb.append("    tags         ").append(tags).append("\n");
        if(assigneeDetails != null)
            sb.append("    assignee_name  ").append(assigneeDetails.getName()).append("\n");

        return sb.toString();

    }

}
