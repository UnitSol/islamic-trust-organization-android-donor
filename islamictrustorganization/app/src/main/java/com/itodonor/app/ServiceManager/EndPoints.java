package com.itodonor.app.ServiceManager;

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
    public static final String kGetProjectUpdate = BaseURL + "get_project_update";
    public static final String kGetProjectUpdateDoc = BaseURL + "get_project_doc";
    public static final String kGetCompletedProjects = BaseURL + "get_complete_projects";
    public static final String kGetOnGoingProjects = BaseURL + "get_on_going_projects";
    public static final String kGetProjectDetail = BaseURL + "get_project_detail";
    public static final String kMakeDonation = BaseURL + "add_donation";
    public static final String kUserProfileUpdate = BaseURL + "update_profile";
    public static final String kGetProjectTypes = "https://islamic-trust.bhattimobiles.com/api/contractor/get_project_types";
    public static final String kDonnorRequest = BaseURL + "donor_request";
    public static final String kGetMyRequest = BaseURL + "get_donor_request";
    public static final String kGetProjectCategory =  "https://islamic-trust.bhattimobiles.com/api/contractor/get_project_categories";
    // [@End Password Reset]

}
