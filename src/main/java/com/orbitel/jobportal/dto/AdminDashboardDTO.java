package com.orbitel.jobportal.dto;

public class AdminDashboardDTO {
    private long totalUsers;
    private long totalJobPosts;
    private long totalApplications;
    private long pendingApplications;
    private long hiredApplications;
    private long totalMessages;
    private long unreadMessages;

    public AdminDashboardDTO(long totalUsers, long totalJobPosts, long totalApplications, long pendingApplications,
            long hiredApplications, long totalMessages, long unreadMessages) {
        this.totalUsers = totalUsers;
        this.totalJobPosts = totalJobPosts;
        this.totalApplications = totalApplications;
        this.pendingApplications = pendingApplications;
        this.hiredApplications = hiredApplications;
        this.totalMessages = totalMessages;
        this.unreadMessages = unreadMessages;
    }

    public AdminDashboardDTO() {
    }
    
    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalJobPosts() {
        return totalJobPosts;
    }

    public void setTotalJobPosts(long totalJobPosts) {
        this.totalJobPosts = totalJobPosts;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getPendingApplications() {
        return pendingApplications;
    }

    public void setPendingApplications(long pendingApplications) {
        this.pendingApplications = pendingApplications;
    }

    public long getHiredApplications() {
        return hiredApplications;
    }

    public void setHiredApplications(long hiredApplications) {
        this.hiredApplications = hiredApplications;
    }

    public long getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(long totalMessages) {
        this.totalMessages = totalMessages;
    }

    public long getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(long unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
}
