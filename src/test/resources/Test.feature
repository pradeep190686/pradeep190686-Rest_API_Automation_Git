Feature:New implementation of API

#@BusinessServicePage
#Scenario Outline: Verify functionality of new landing page
#
#Given The Test Data is Ready
#When I submit the post request
#Then I validate the Response Code
#And I check if the Response is compliant with the Schema
#
#  Examples:


#  Scenario Outline: I verify new eay of doing API testing with Selenium
#    Given I access the application to procure temporary token for the application <application_path> with <username> and <password>
#    Then I pass get request witn <url_path> and <query_parameters>
#
#      Examples:
#        |application_path|username|password|url_path|query_parameters|
#        |https://qa.mar.sterrain.com/account/login|qa-admin-1|Notasecret1!|https://qa.mar.sterrain.com/api/v0_1/user_sessions||


#    Scenario Outline: I verify new eay of doing API testing with Selenium
#    Given I access the application to procure temporary token for the application <application_path> with <username> and <password>
#
#      Examples:
#     |application_path|username|password|
#     |https://qa.mar.sterrain.com/account/login|qa-admin-1|Notasecret1!|

   Scenario Outline: Get Request Validation

     Then I pass get request witn <url_path> and <query_parameters> and verify response with <schema>

       Examples:
       |url_path|query_parameters|schema|
       |/api/v0_1/security_segments?per_page=max||securitySegments_schema.json|
       |/api/v0_1/user_sessions||          |


#  Scenario Outline: Post Request Validation
#
#    Then I pass post request with <url_path>,<body_data>,<query_parameters> and verify response with <schema>
#
#    Examples:
#      |url_path|body_data|query_parameters|schema|
#      |/api/v0_1/comments|comment|||

