# Project Name

Drug System is a comprehensive application designed to efficiently manage drug and prescription data and processes for the pharmacy.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Requirements

## Installation

1. Clone the repository:
    ```sh
    git https://github.com/vastafi/Project-DAA
    cd DrugSystem
    ```

2. Install Maven (if not already installed):
    ```sh
    brew install maven
    ```

3. Verify Maven installation:
    ```sh
    mvn -version
    ```

4. Build the project:
    ```sh
    mvn clean install
    ```

5. Create and edit the `application.properties` file (optional but recommended)

## Usage

1. Start the Services:
    ```sh
   # Start stock-service
   docker-compose up -d stock-service

   # Start prescription-service
   docker-compose up -d prescription-service

   # Start auth-server
   docker-compose up -d auth-server
    ```
2. **Access the application:**

   - `drug-gateway` will be available at `http://localhost:8080`
   - `drug-discovery` will be available at `http://localhost:8761`
   - `stock-service` will be available at `http://localhost:8082`
   - `prescription-service` will be available at `http://localhost:8081`
   - `auth-server` (Keycloak) will be available at `http://localhost:9000`

## Keycloak Setup

1. **Access Keycloak Admin Console:**

   Open a web browser and go to `http://localhost:9000`. Log in with the admin credentials.

2. **Verify Realm and Roles:**

   - Verify that the `drug-system` realm has been imported correctly.
   - Verify that the roles (`ADMIN`, `DOCTOR`, `USER`, `PHARMACIST`) have been set up correctly.

## Front-End Setup

1. **Navigate to the `drug-front` directory:**

    ```sh
    cd path/to/drug-front
    ```

2. **Install dependencies:**

    ```sh
    npm install
    ```

3. **Start the front-end application:**

    ```sh
    npm run serve
    ```

4. **Access the front-end application:**

   App running at:
- Local:   http://localhost:3000/
- Network: http://172.18.6.115:3000/

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.