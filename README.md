Unit 8: Group Milestone - README
===

# HALTING HUNGER

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Schema](#Schema)

## Overview
### Description
Halting Hunger is a platform that connects food donors and volunteers, who are willing to deliver food to people in need. Donor can post food details and location in the app. Volunteers can check for donors based on their location and pick up the food from them. 

### App Evaluation
- **Category:** Food & Drink
- **Mobile:** This app will be primarily developed for usage in mobile platform
- **Story:** Donors can donate food by adding details. Volunteers will pickup the food from location posted by donor and deliver to needy.
- **Market:** Individuals who are interested in donating food and volunteering can use this app. Helps in reducing food wastage.
- **Habit:** This app can be used whenever people have excess food and they are looking to donate it, thus also reducing food wastage
- **Scope:** Initially this app can connect donors with volunteers. We can further extend this by adding local organization and donors can choose the organization based on their preferences. 

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can sign up as Donor or Volunteer
* Donors can create food donation post
* Donors can check the status of donation pickup and details of volunteer who will be picking the food
* Donors can cancel the donation
* Volunteers can filter based on location and see the list of all donations ready to be picked up
* Volunteers can select the donation he/she chooses to pickup
* Volunteers can see the list of upcoming pickups confirmed by them
* Volunteers can cancel/complete the confirmed upcoming pickups

**Optional Nice-to-have Stories**

* Donors can add pictures of food in the post created
* Volunteers can create food request posts based on need
* Donors can opt to choose the organizations/available volunteers they want to donate to (can be from a map view)
* A messaging platform to enhance communication between donors and volunteers


### 2. Screen Archetypes

* Login - Donor and Volunteer can login to their respective accounts
* Register- Users can signup to the application by providing user details and selecting a role (Donor/Volunteer).
* Donor Homepage - 
    * Initiate Donation Tab - Allows user to fill food details like quantity, pickup time and location.
    * Check Status Tab - Can check the status of the post.  Status will be updated once volunteer claims the donations.
* Volunteer Homepage - 
    * List Donations Tab - Consists of list of donors based on the user location.
    * Upcoming Pickups Tab - If volunteer claims the donation, then pickup status for particular donation will be updated.

### 3. Navigation

**Tab Navigation** (Tab to Screen)
Donor HomeScreen:
* Initiate Donation
* Check Status

Volunteer HomeScreen:
* List Donations
* Upcoming Pickups

**Flow Navigation** (Screen to Screen)
* Main screen
   * On clicking Signup, goes to Signup screen
   * On clicking Login, goes to user's homescreen (whoever is already signed in)
* Signup Screen
   * On clicking Signup, goes to user's homescreen
* Initiate Donation screen
   * On clicking Confirm Donation, goes to Check Status screen
* List Donations screen
   * On clicking Pickup, goes to Upcoming Pickups screen

## Wireframes

### [BONUS] Digital Wireframes & Mockups
<img src="https://imgur.com/LqGncqp.jpg" height=500>

### [BONUS] Interactive Prototype
<img src="https://imgur.com/cGGnPMr.gif" width=500>


## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | donor        | Pointer to User| post author-donor |
   | title         | String     | name of the food item |
   | details       | String   | details about the food item |
   | vegetarian       | Boolean   | gives additional details |
   | non-vegetarian       | Boolean   | gives additional details |
   | homemade       | Boolean   | gives additional details |
   | quantity | String   | quantity of the food item |
   | startTime     | DateTime | start date-time for pickup |
   | endTime     | DateTime | end date-time for pickup |
   | location     | String | location for pickup |
   | zipcode     | Number | zipcode for pickup |
   | status        | String| status of the post made |
   | volunteer        | Pointer to User| assigned volunteer to pickup |
   
#### User

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | userId      | String   | unique id for the user(default field) |
   | password        | String| user password|
   | name        | String| donor/volunteer name|
   | phone        | Number| user phone  number|
   | email        | String| user email id|
   | address         | String     | user location |
   | type       | String   | user type(donor/volunteer) |
   | organization | String   | organization to which user belongs to |
   
### Networking
#### List of network requests by screen
   - SignUp Screen
        - (Create/POST) Add a new user
        ```swift
         ParseUser user = new ParseUser();
         user.setUsername("username");
         user.setPassword("password");
         user.setEmail("useremail@example.com");
         user.put("phone", "650-253-0000");
         user.put("type","Donor")
         user.put("address","123 street")
         user.signUpInBackground(new SignUpCallback() {
         public void done(ParseException e) {
         if (e == null) {
       // sign up successful 
        } else {
      // Sign up didn't succeed. Look at the ParseException
       }
      }
     });
      ```
         
         
   - Login Screen
        - (Read/Get) Authenticate user credentials
        ```swift
        ParseUser.logInInBackground("user1","user1password", new LogInCallback() {
      public void done(ParseUser user, ParseException e) {
     if (user != null) {
       // user is logged in.
        } else {
          // Login failed. Look at the ParseException to see what happened.
        }
      }
        });
        ```
   - Donor Home Page
        - (Create/POST) Create a new post object with food details
       ```swift  
        ParseObject foodDetails = new ParseObject("FoodPost");
        foodDetails.put("donor", parseUser.getCurrentUser());
        foodDetails.put("title", "Food available");
        foodDetails.put("details", "Briyani and roti");
        foodDetails.put("vegetarian", false);
        foodDetails.put("non-vegetarian", true);
        foodDetails.put("homemade", false);
        foodDetails.put("quantity", "5 packets briyani and 10 rotis");
        foodDetails.put("startTime", "2021-5-20 09:30:00" );
        foodDetails.put("endTime","2021-5-20 11:30:00" );
        foodDetails.put("location", "Arlington,Texas");
        foodDetails.put("zipcode", 76013);
        foodDetails.put("status", "Waiting for confirmation from Volunteer");
        foodDetails.put("volunteer", );
        foodDetails.saveInBackground();
     ```
     
        
       - (Update/Put) Update status of post
  ```swift
       ParseQuery<ParseObject> query = ParseQuery.getQuery("FoodPost");

      // Retrieve the food post object by id
      query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
      public void done(ParseObject foodDetails, ParseException e) {
      if (e == null) {
      // update the pick up status In this case, it can be either done or cancel
          foodDetails.put("status", "cancel");
          foodDetails.saveInBackground();
        }
      }
      });
```   

        
  - Volunteer Home Page
      - (Read/Get) Query all posts based on user location
      ```swift
      ParseQuery<ParseObject> query = ParseQuery.getQuery("FoodPost");
    query.fromLocalDatastore();
    query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
      public void done(ParseObject object, ParseException e) {
    if (e == null) {
      // object will be the food post(posted by donor)
    } else {
      // something went wrong
    }
    }
    });
    ```

- (Update/Put) Update the pickup status
   ```swift  
   ParseQuery<ParseObject> query = ParseQuery.getQuery("FoodPost");

  // Retrieve the food post object by id
  query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
  public void done(ParseObject foodDetails, ParseException e) {
    if (e == null) {
      // update the pick up status In this case, it can be either done or cancel
      foodDetails.put("status", "pickup confirmed");
      foodDetails.put("volunteer", parseUser.getCurrentUser());
      foodDetails.saveInBackground();
    }
  }
  });
    ```  
