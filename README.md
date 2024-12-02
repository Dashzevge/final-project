README Instructions:

PROBLEM STATEMENT:
Finding local, short-term jobs can be difficult for skilled tradespeople like maintenance workers, cleaners, plumbers, electricians, and painters.
Generalized job platforms fail to address the specific needs of these professionals and their potential clients. 
Homeowners and businesses also struggle to locate trustworthy, qualified workers quickly and affordably.

Goal:
To create a dedicated platform that connects skilled tradespeople with clients, providing an easy and efficient way to find, book, payment and manage short-term skilled labor opportunities.

REQUIREMENT ANALYSIS:

Functional Requirements:

User Registration and Login:
Professionals and Clients can register with relevant details.
Role-based access (Professionals and Clients).

Job Listings:
Clients can post job requests(booking) specifying requirements, location, and budget.
Professionals can browse and apply for jobs matching their expertise.

Clients Profiles:
Clients can create and modified profiles showcasing their skills, certifications, and other details.

Professionals Profiles:
Professionals can create and modified profiles showcasing their skills, certifications, and other details.

Search and Filters:
Clients can search for workers based on location, availability, and ratings.
Professionals can filter jobs based on location, type, and budget.

Booking System:
Clients can book tradespeople for specific dates and times.

Review and Ratings:
Both clients and professionals can rate and review each other after job completion.

Non-Functional Requirements:

Performance:
The platform should handle up to 10,000 simultaneous users.

Security:
Secure user authentication, authorization and data encryption.

Scalability:
The platform should scale to accommodate growing user bases.

Usability:
The interface must be user-friendly and intuitive.

Availability:
Ensure 99.9% uptime


SOFTWARE MODELING:
Include UML diagrams such as use case diagrams, class diagrams, or sequence diagrams as applicable.

ARCHITECTURE:
1. Presentation Layer (UI Layer)
    Purpose:
    Handles all interactions with the user, including collecting input, displaying output, and ensuring an intuitive user experience.
   Responsibilities:
    Display job listings and worker profiles.
    Handle user inputs (e.g., job posting, worker applications).
    Communicate with the business logic layer via RESTful APIs or service calls.
   Implementation:
    Frontend Framework: Use a combination of Thymeleaf and Bootstrap for server-side rendering and responsive design.
2. Business Logic Layer (Service Layer)
    Purpose:
    Encapsulates the core business logic, processes user requests, and enforces business rules.
   Responsibilities:
    Validate and process user actions (e.g., job postings, booking workers).
    Manage user sessions and roles (e.g., admin vs. regular user permissions).
    Interface with the data access layer for database operations.
   Implementation:
    Spring Service Classes:
     Write service classes that manage business logic.
     Annotate service classes with @Service.
3. Data Access Layer (DAO Layer)
    Purpose:
     Manages direct interactions with the database, handling CRUD operations and ensuring data integrity.
    Responsibilities:
     Provide a clean interface for data operations (repositories).
     Abstract raw database queries using an ORM (like JPA/Hibernate).
     Ensure database-related exceptions are handled gracefully.
    Implementation:
     Spring Data JPA Repositories:
      Use @Repository for defining data access interfaces.
      Create custom queries using JPQL or native SQL as needed.
4. Database Layer
    Purpose:
     Stores application data persistently, including user accounts, job listings, worker reviews, and bookings.
    Responsibilities:
     Define a normalized database schema to handle relationships.
     Support scalability with optimized indexing and queries.
    Implementation:
     Database Structure: MySQL database with tables like Users, Jobs, Categories, and Bookings.
5. Inter-Layer Communication
    Presentation to Business Logic:
     Use Spring MVC controllers annotated with @Controller or @RestController to expose endpoints for the UI layer.
6. Error Handling Across Layers
     Centralize error handling in the controller layer using Spring’s @RestControllerAdvice

ER DIAGRAM:
Include a detailed ER diagram showing your database structure and relationships.




SOFTWARE SETUP INSTRUCTIONS:
Prerequisites:
    Install MySQL (version 8 or higher).
    Install Java JDK (version 17 or higher).
    Install Spring Boot and Thymeleaf.
    Install Maven.
    Install Intellij IDEA (version 21 or higher).

Include necessary screenshots (e.g., database configuration, running the application).
 