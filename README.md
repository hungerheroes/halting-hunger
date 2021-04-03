Unit 8: Group Milestone - README
===

# HALTING HUNGER

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

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