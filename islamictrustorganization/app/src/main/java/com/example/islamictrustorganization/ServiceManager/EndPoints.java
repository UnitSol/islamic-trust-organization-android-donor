package com.example.islamictrustorganization.ServiceManager;

public class EndPoints {

    public static final String BaseURL = "https://islamic-trust.bhattimobiles.com/api/donor/";
    public static final String BaseAuthURL = "https://islamic-trust.bhattimobiles.com/api/";

    // [USER MANAGEMENT]
    public static final String kLogin = BaseAuthURL + "donor_login";

    // [@Start Password Reset]
    public static final String kSendResetPasswordCode = BaseAuthURL + "send_password_reset_code";
    public static final String kValidateResetPasswordCode = BaseAuthURL + "validate_reset_code";
    public static final String kUpdatePassword = BaseAuthURL + "update_password";
    // [@End Password Reset]

    public static final String KGetDashboardDetail = BaseURL + "dashboard_detail";

    // [@Start Password Reset]
    public static final String kGetProjects = BaseURL + "get_projects";
    public static final String kGetCompletedProjects = BaseURL + "get_complete_projects";
    public static final String kGetOnGoingProjects = BaseURL + "get_on_going_projects";
    public static final String kGetProjectDetail = BaseURL + "get_project_detail";
    public static final String kMakeDonation = BaseURL + "add_donation";
    public static final String kUserProfileUpdate = BaseURL + "update_profile";
    // [@End Password Reset]

}
