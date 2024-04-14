# FnBuddy User Guide

Welcome to the FnBuddy User Guide! This comprehensive guide is designed to help you navigate and utilise the FnBuddy part-time employee contact management application with ease. Whether you're a seasoned restaurant manager or new to the role, this guide will serve as your trusted companion, empowering you to streamline your operations and enhance your team management capabilities. FnBuddy can manage contacts optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FnBuddy can get your contact management tasks done faster than traditional GUI apps.

This user guide is tailored specifically for restaurant managers who are responsible for liaising with part-time employee records and managing their payroll. We assume that you have a basic understanding of using software applications and are familiar with common restaurant operations and terminology.

## Table of Contents

- [FnBuddy User Guide](#fnbuddy-user-guide)
  - [Table of Contents](#table-of-contents)
  - [Introduction to FnBuddy](#introduction-to-fnbuddy)
  - [Quick start](#quick-start)
  - [Features](#features)
    - [ℹ️ Notes about the command format:](#ℹ️-notes-about-the-command-format)
    - [Adding a person `add`](#adding-a-person-add)
    - [Listing contacts `list`](#listing-contacts-list)
    - [Deleting a person `delete`](#deleting-a-person-delete)
    - [Editing a person `edit`](#editing-a-person-edit)
    - [Locating a person by name `find`](#locating-a-person-by-name-find)
    - [Clear all contacts `clear`](#clear-all-contacts-clear)
    - [Exiting the program `exit`](#exiting-the-program-exit)
    - [Archiving the person `archive`](#archiving-the-person-archive)
    - [Unarchive the person `unarchive`](#unarchive-the-person-unarchive)
    - [Retrieving payroll `payroll`](#retrieving-payroll-payroll)
    - [Schedule employees `schedule`](#schedule-employees-schedule)
    - [Unschedule employees `unschedule`](#unschedule-employees-unschedule)
    - [Saving the data](#saving-the-data)
      - [Modifying saved data (For advanced users only!)](#modifying-saved-data-for-advanced-users-only)
  - [Pages](#pages)
    - [Command Type](#command-type)
  - [Known issues](#known-issues)
  - [Glossary](#glossary)
  - [FAQ](#faq)
  - [Command summary](#command-summary)

## Introduction to FnBuddy

The primary purpose of this user guide is to provide you with a comprehensive resource that will enable you to fully harness the capabilities of FnBuddy. Whether you need to create, view, update, or delete employee contacts, track working hours, assign roles, or simply navigate the application seamlessly, this guide will be your trusty companion, ensuring that you can effectively manage your team and streamline your restaurant's operations.

FnBuddy is an innovative employee contact management application designed specifically for restaurant managers. It offers a user-friendly interface (both CLI and GUI) that allows you to effortlessly create, manage, and maintain contact records for all your employees. With FnBuddy, you can store essential information such as contact details, banking information, and work schedules, ensuring efficient communication and accurate payroll calculations.

## Quick start

1. Ensure you have Java 11 or above installed on your Computer.
2. Download the latest fnbuddy.jar from [here](https://github.com/AY2324S2-CS2103T-T17-4/tp/releases/tag/v1.2).
3. Copy the file to the folder you want to use as the home folder for your FnBuddy.
4. Open a command terminal, cd (change directory) into the folder you put the jar file in, and use the `java -jar fnbuddy.jar` command to run the application.
5. A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.

|![UI](./images/Ui.png)|
|------------------------------|
|            *FnBuddy GUI*             |
6. Type the command in the command box and press Enter to execute it. e.g., typing `help` and pressing Enter will open the help window.

Some example commands you can try:
- `add -fn Javier -ln Tan -p 98749874 -s m -pr 10.5 -a 123 Street -b posb 420053040` : Adds a contact named Javier Tan to FnBuddy.
- `list` : Lists all contacts.
- `delete 98749874` : Deletes the contact associated with the phone number 98749874 from FnBuddy.
- `edit 91234567 -a NUS` : Edits the address of the contact associated with phone number 91234567 to NUS.
- `hours 91234567 50` : Saves the number of hours worked by the employee.
- `find james` : Searches the address book for a person whose name matches “james”.
- `clear` : Deletes all contacts.
- `exit` : Exits the app.

Refer to the [Features](#Features) section below for details of each command.

## Features

### ℹ️ Notes about the command format:

- Words in UPPER_CASE are the parameters to be supplied by the user. e.g., in `add -fn FIRST_NAME -ln LAST_NAME`, FIRST_NAME and LAST_NAME are parameters which can be used as `add -fn Javier -ln Tan`.
- Items in square brackets are optional. e.g., `-fn FIRST_NAME -ln LAST_NAME [-t TAG]` can be used as `-fn Javier -ln Tan -t/waiter` or as `-fn Javier -ln Tan`.
- Items with `…` after them can be used multiple times, including zero times. e.g., `[-t TAG]…` can be used as `-t cook -t waiter -t dishwasher`, etc.
- Parameters can be in any order. e.g., if the command specifies `-fn FIRST_NAME -ln LAST_NAME`, `-ln LAST_NAME -fn FIRST_NAME` is also acceptable.
- Extraneous parameters for commands that do not take in parameters (such as help, exit, and clear) will be ignored. e.g., if the command specifies `help 123`, it will be interpreted as `help`.
- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines, as space characters surrounding line-breaks may be omitted when copied over to the application.
- There should be spaces between the flags and the parameters. e.g., `add -fn Javier -ln Tan` is correct, while `add -fn Javier-ln Tan` is incorrect. However, extra spaces are allowed. e.g., `add    -fn    Javier    -ln    Tan` is also correct.
- The types of commands are divided into 3 categories: Main Page Commands, Payroll Page Commands, and Schedule Page 
  Commands. For more details, refer to the [Pages](#Pages) section below.

### Adding a person `add`

Adds a person’s contact to FnBuddy.

Format: `add -fn FIRST_NAME -ln LAST_NAME -p PHONE_NUMBER -s SEX -pr PAY_RATE -a ADDRESS [-b BANK_DETAILS] [-t TAG]…`

Command Type: Main Page Command

Example:
- `add -fn John -ln Doe -p 91860934 -s m -pr 20.50 -a 123 Main St, City`
- `add -fn Jane -ln Smith -p 98765432 -s f -pr 25.50 -a 432 Orchard Road -b posb 123456789 -t waiter -t bartender`

Note: All contacts added are compressed to only show `FIRST_NAME`, `LAST_NAME` and `PHONE_NUMBER` by default. To view all of the contact's information, simply click on the contact to expand.

### Listing contacts `list`

Shows a list of contacts in FnBuddy depending on which you'd like to view.

Format: `list LIST_TYPE`

Command Type: Main Page Command

Example:
- `list all` shows all contacts in FnBuddy.
- `list main` shows all un-archived contacts in FnBuddy.
- `list archive` shows all archived contacts in FnBuddy.

### Deleting a person `delete`

Deletes the specified person from FnBuddy.

Format: `delete PHONE_NUMBER`

Command Type: Main Page Command

Example:
- `delete 91860934` deletes the person with the number 91860934 from FnBuddy.

### Editing a person `edit`

Edits an existing person in FnBuddy.

Format: `edit PHONE_NUMBER [-fn FIRST_NAME] [-ln LAST_NAME] [-p PHONE_NUMBER] [-s SEX] [-pr PAY_RATE] [-a ADDRESS] [-b BANK_DETAILS] [-t TAG]…`

Command Type: Main Page Command

Example:
- `edit 91860934 -a Room 504, Marina Bay Sands -pr 25` Edits the address of the person with the phone number 91860934 to Room 504 Marina Bay Sands, and their pay rate to 25 dollars per hour, respectively.
- `edit 98765432 -t` Clears all existing tags from the person with the phone number 98765432.

### Locating a person by name `find`

Finds persons whose names match any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

Command Type: Main Page Command

Example:
- `find tan` returns all contacts with names matching `tan`.

![Find UI](./images/Find_UI.png)

### Clear all contacts `clear`

Delete all employee contacts.

Format: `clear`

Command Type: Main Page Command

**WARNING!** This action is permanent and non-reversible! Make sure that you want to clear FnBuddy before you execute the command.

### Exiting the program `exit`

Exits the program.

Format: `exit`

### Archiving the person `archive`

Archives the person's contact so that it is hidden from the main list of contacts.

Format: `archive PHONE_NUMBER`

Command Type: Main Page Command

Example:
- `archive 91860934` archives the person with the number 91860934 from FnBuddy's main list of contacts.

### Unarchive the person `unarchive`

Unarchive the person's contact so that it is shown in the main list of contacts.

Format: `unarchive PHONE_NUMBER`

Command Type: Main Page Command

Example:
- `unarchive 91860934` un-archives the person with the number 91860934 from FnBuddy's main list of contacts.

### Retrieving payroll `payroll`

Retrieve employee's payroll for a given start and end date

Format: `payroll -sd START_DATE -ed END_DATE` where `START_DATE` and `END_DATE` are in the format `YYYY-MM-DD`.

Command Type: Payroll Command

Example:
- `payroll -sd 2024-04-01 -ed 2024-04-30` calculates the payroll of all employees that have worked within 1st April 2024 and 30th April 2024.

Note: Employee's payroll is calculated by multiplying 8 to their respective `PAY_RATE`. We are assuming each shift is 8 hours.

### Schedule employees `schedule`

Adds a person in FnBuddy to the schedule on a specified date.

Format: `schedule PHONE_NUMBER DATE` where `DATE` is in the format `YYYY-MM-DD`.

Command Type: Schedule Command

Example:
- `schedule 91860934 2024-04-01` Adds the person with the phone number 91860934 to the schedule on 4th April 2024.

### Unschedule employees `unschedule`

Removes a person in FnBuddy from the schedule on a specified date.

Format: `unschedule PHONE_NUMBER DATE` where `DATE` is in the format `YYYY-MM-DD`.

Command Type: Schedule Command

Example:
- `unschedule 91860934 2024-04-01` Removes the person with the phone number 91860934 from the schedule on 4th April 2024.

### Saving the data

FnBuddy data is stored in the hard disk automatically after any command that changes the data. Rest assured, there is no need to save manually.

#### Modifying saved data (For advanced users only!)

You may edit the JSON files directly in the data folder if you wish to make changes to the data without using the app. However,
if the JSON files are no longer in the correct format, the app may not be able to read the data correctly and may crash or have unexpected behaviour.

If you still wish to edit the JSON files, for the schedule.json file, note that there are additional constraints you must follow aside from the format:
- The contact in the schedule must exist in the main list of contacts i.e. you should not add a new contact that does not exist, in the schedule.json file.
- There should not be duplicate entries for the same contact on the same date.

## Pages
There are 3 main pages in the application:
1. **Main Page** - This page shows all the contacts in the application. You can view the details of each contact by clicking on the contact.
2. **Payroll Page** - This page shows the payroll of all employees for a given date range. You can view the details of each contact by clicking on the contact.
3. **Schedule Page** - This page shows the schedule of all employees for a given date. You can view the details of each contact by clicking on the contact.

### Command Type
With the 3 different pages, different types of commands can also be used to navigate the application. These types are as follows:
1. **Main Page Commands** - These commands when used will navigate to the main page of the application.
2. **Payroll Page Commands** - These commands when used will navigate to the payroll page of the application.
3. **Schedule Page Commands** - These commands when used will navigate to the schedule page of the application.

## Known issues
1. When using multiple screens, if you move the application to a secondary screen and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
   a. Alternatively, for Windows users, you can press Shift and right-click the program icon on the taskbar, Select Move, and use your left or right arrow keys to move the window until the window appears.

## Glossary

- **CLI** - Command Line Interface
    - A text-based interface used to interact with software applications. In the context of this application, the 
      CLI is used to input commands to manage employee contacts. You can type commands into the command box as shown 
      below.

      |![CLI](./images/CommandBox.png)|
      |------------------------------|
      |            *Command Box used to input commands*             |
- **GUI** - Graphical User Interface
    - A visual interface that allows users to interact with software applications using graphical elements such as 
      windows, buttons, and icons. In the context of this application, the GUI provides a visual representation of 
      the employee contacts and allows users to interact with the application using buttons and text fields. However,
      our GUI is not as feature-rich as traditional GUI applications and is designed to be used in conjunction with 
      the CLI to provide the full functionality of the application.

      | ![GUI](./images/Ui.png)                               |
      |-------------------------------------------------------|
      | *How the app displays information in the FnBuddy GUI* |
- **FnBuddy** - The name of the application
- **Employee** - A person who works part-time at a restaurant
- **Contact** - A record of an employee in FnBuddy
- **Payroll** - The total amount of money paid to employees for their work

## FAQ

- **Q: How is the FnBuddy’s data stored?**
    - A: FnBuddy stores its data in a local file on your computer.

- **Q: I prefer clicking buttons to navigate applications. Will FnBuddy be upgrading its GUI to be friendlier to such users?**
    - A: As FnBuddy is purposefully targeted towards users who prefer typing, there are currently no plans to add features that support GUI interactions to replace the command-line style of the app.

- **Q: Is there any quick referral in the app itself for commands that I forget?**
    - A: Click on the help button at the top left of the application’s window for a commands list dropdown.

- **Q: Can I list down an employee with more than 2 names?**
    - A: As FnBuddy only supports the `FIRST_NAME` `LAST_NAME` format, the best solution currently is to list the middle name with either the first or the last. Example: Chua Xun Hao can be saved as `-fn Xun Hao -ln Chua`.

- **Q: My employee uses a foreign phone number, how can that be reflected in the app?**
    - A: FnBuddy does not currently support phone number region differentiation and only supports 8-digit inputs after the `-p` flag. In future updates, we will add contact regions as a feature.

- **Q: Do I need to provide all the details of an employee when creating the contact?**
    - A: No, optional details do not need to be added and can be edited into the contact later on if required. Refer to the add contact feature to view which details are compulsory and optional.

## Command summary

Here's the updated table with the new features added:

| Command | Description                                                                        | Format | Examples                                                                                                                                                                                  |
|---------|------------------------------------------------------------------------------------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Adding a person** | Adds a person's contact to FnBuddy.                                                | `add -fn FIRST_NAME -ln LAST_NAME -p PHONE_NUMBER -s SEX -pr PAY_RATE -a ADDRESS [-b BANK_DETAILS] [-t TAG]...` | `add -fn John -ln Doe -p 91860934 -s m -pr 20.50 -a 123 Main St City`<br>`add -fn Jane -ln Smith -p 98765432 -s f -pr 25.50 -a 432 Orchard Road -b posb 123456789 -t waiter -t bartender` |
| **Listing all persons** | Shows a list of all persons in FnBuddy.                                            | `list` | 1. `list all`<br/> 2. `list archive` <br/> 3. `list main`                                                                                                                                 |
| **Deleting a person** | Deletes the specified person from FnBuddy.                                         | `delete PHONE_NUMBER` | `delete 91860934`                                                                                                                                                                         |
| **Editing a person** | Edits an existing person in FnBuddy.                                               | `edit PHONE_NUMBER [-fn FIRST_NAME] [-ln LAST_NAME] [-p PHONE_NUMBER] [-s SEX] [-pr PAY_RATE] [-a ADDRESS] [-b BANK_DETAILS] [-t TAG]...` | `edit 91860934 -a Room 504 Marina Bay Sands -pr 25`<br>`edit 98765432 -t`                                                                                                                 |
| **Locating a person by name** | Finds persons whose names match any of the given keywords.                         | `find KEYWORD [MORE_KEYWORDS]` | `find john tan`                                                                                                                                                                           |
| **Clear all contacts** | Delete all employee contacts.                                                      | `clear` | -                                                                                                                                                                                         |
| **Exiting the program** | Exits the program.                                                                 | `exit` | -                                                                                                                                                                                         |
| **Archiving a person** | Archives the person's contact so that it is hidden from the main list of contacts. | `archive PHONE_NUMBER` | `archive 91860934`                                                                                                                                                                        |
| **Un-archiving a person** | Un-archives the person's contact so that it is shown in the main list of contacts. | `unarchive PHONE_NUMBER` | `unarchive 91860934`                                                                                                                                                                      |
| **Retrieving payroll** | Retrieve employee's payroll for a given start and end date.                        | `payroll -sd START_DATE -ed END_DATE` | `payroll -sd 2024-04-01 -ed 2024-04-30`                                                                                                                                                   |
| **Schedule employees** | Adds a person in FnBuddy to the schedule on a specified date.                      | `schedule PHONE_NUMBER DATE` | `schedule 91860934 2024-04-01`                                                                                                                                                            |
| **Unschedule employees** | Removes a person in FnBuddy from the schedule on a specified date.                 | `unschedule PHONE_NUMBER DATE` | `unschedule 91860934 2024-04-01`                                                                                                                                                          |
