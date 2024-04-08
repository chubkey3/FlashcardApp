# Flashcard Study Tool

## What is this project?

This project is **online flashcard tool** that helps to memorize key terms and definitions for exams. The application will
allow users to create flashcards with one side having the term or question and the other having the definition or answer.
Because of its wide applicability to countless courses or subjects, any student can use this application to help them prepare for
exams or just to review course material. In addition, this application increases the availability of using flashcards to study
as they don't require stationary to be used/bought.

## Why am I making this project?

I created this project because I always heard from friends how useful flashcards can be to study, especially when used in memorization heavy courses,
but I never had the motivation to physically write out
the flashcards. Having it now **digitized** will make it much more accessible to me and others which has the potential to make studying
much easier going forward. Furthermore, all the online flashcard tools I've come across have some paid component so this application
also acts as an open-source alternative to popular paid services such as Quizlet.

## User Stories

As a user, I want to be able to create a new flashcard and add it to a list of flashcards

As a user, I want to be able to view the flashcards in a list of flashcards

As a user, I want to be able to test myself on a subject by randomly seeing flashcards within a list of flashcards

As a user, I want to be able to save a collection of flashcards and be able to view them at a later time

PHASE 2

As a user, I want the option of saving flashcard collections to a file
As a user, I want the option of reloading flashcard collections from a file and having the ability to test myself on them

PHASE 3

As a user, I want to be able to add multiple flashcards to a flashcard list 
and view them on a panel.
As a user, I want a button available to add flashcards to a flashcard list
As a user, I want a button available to test flashcards in a flashcard list

As a user, I want to be able to load and save the state of the application using buttons

Instructions for Grader

To generate the first required action, switch to the "Add" tab and enter a
question and answer into the fields. Once finished, click the "Add" button to add a flashcard
to the current flashcard list.

To generate the second required action, switch to the "Test" tab and click "New Test". It is
important to note at least once flashcard should be present at this time. Once you click the button, a new test will
start and a question will appear to the right of the button. Once you think of an answer, you can enter
it in the text field and click "Check" once done. If you are correct, an image of a cat with its thumbs up will appear
and a cat with its thumbs down will appear if your answer is incorrect. Once done, you can click the "Next" button to
view the next flashcard. You can continue until no more flashcards are left to be tested.

The visual component can be found in the test tab when answering questions. If your answer matches the saved answer,
an image of a cat with its thumbs up will appear signifying a correct answer. If your answer does not match the saved answer,
an image of a cat with its thumbs down will appear signifying an incorrect answer.

To save and load data, you can switch to the Save/Load tab and click either the "Save" button to save the current flashcard state
in memory or click "Load" to load the saved flashcard state into the current flashcard state.

PHASE 4: Task 2

Sample of Events

Sun Apr 07 14:39:43 PDT 2024
Added flashcard to flashcard list
Sun Apr 07 14:39:54 PDT 2024
Added flashcard to flashcard list
Sun Apr 07 14:39:59 PDT 2024
Added flashcard to flashcard list
Sun Apr 07 14:40:01 PDT 2024
Removed flashcard from flashcard list
Sun Apr 07 14:40:05 PDT 2024
New flashcard test instance started
Sun Apr 07 14:40:05 PDT 2024
Random flashcard chosen
Sun Apr 07 14:40:10 PDT 2024
Incorrectly answered '13.6 billion years' to 'how old is the universe'
Sun Apr 07 14:40:13 PDT 2024
Correctly answered '13.7 billion years' to 'how old is the universe'
Sun Apr 07 14:40:13 PDT 2024
Random flashcard chosen
Sun Apr 07 14:40:16 PDT 2024
Correctly answered 'chips' to 'potato ___'

PHASE 4: Task 3

One refactor I can do is to group my tab classes into another class that encompasses all the tabs into a neat container.
This prevents clutter and increases readability in my FlashcardApp class as it no longer has to manage all the tabs in addition
to its original functionality. This also improves the cohesion of my FlashcardApp class as it has a more narrow rage of responsibilities.

Right now, my FlashcardApp class deals with a lot and I think reducing its responsibilities would be beneficial. One
way I can accomplish this is by splitting up the actual UI and flashcard functionality into separate classes. I think
just a standalone class for UI components minus all functionality would be much clean and concise to read. I don't currently
know how I would approach this but some ideas would be making FlashcardApp hold another class called FlashcardManager that
deals with all the functionality of flashcards. Another method would be using inheritance to give the parent flashcard functionality
and the child UI functionality which again further simplifies the FlashcardApp class and improves its overall cohesion. 