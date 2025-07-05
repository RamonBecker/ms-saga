# 📘 Microservices with Orchestrated Saga

This repository presents a personal project focused on handling **distributed transactions** using the **Orchestrated Saga Pattern**. The goal is to build a robust microservices architecture capable of handling failures and maintaining data consistency without relying on the 2PC protocol.

---

## 📊 Architecture Flowcharts

Below are the main diagrams that illustrate the behavior of the system using the **Orchestrated Saga Pattern** and the interactions between the microservices involved:

### 🧭 Architecture Overview

> Diagram showing the microservices, the saga orchestrator, and the databases involved, all connected through Apache Kafka.

![Data Flow Orchestrated](https://github.com/user-attachments/assets/d1bbaecf-60b7-417e-b92f-8d2feb7c556c)
![Saga Choreografy](https://github.com/user-attachments/assets/d5f3fd8a-2fdb-415b-a436-327d459c35c2)


---

## 🧱 Project Overview

This project implements a microservices architecture that simulates a complete flow of ordering, payment, and inventory update. Coordination between services is managed through the **Orchestrated Saga Pattern**, using asynchronous communication via **Apache Kafka**.

---

## 🔧 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Apache Kafka** (event-driven communication)
- **PostgreSQL** (relational database)
- **MongoDB** (non-relational database for orders)
- **Docker** & **docker-compose** (containerized environment)
- **Lombok**, **Spring Data**, **Spring Cloud Stream**

---

## 📦 Architecture Structure

The architecture is composed of 5 microservices:

### 1. **Saga Orchestrator**
- Manages the distributed transaction flow
- Controls the order of operations and executes compensating actions if a failure occurs

### 2. **Order Service**
- Receives order creation requests
- Stores data in MongoDB
- Emits events to start the saga

### 3. **Payment Service**
- Processes payment attempts
- Performs compensation if needed

### 4. **Inventory Service**
- Updates product stock
- Validates availability
- Compensates reservation in case of rollback

### 5. **Product Service**
- Maintains product information

---

## ▶️ Orchestrated Saga Flow (Success Scenario)

> Represents the full flow when all steps in the transaction are completed successfully:

1. Order is created
2. Payment is authorized
3. Inventory is updated

📌 `docs/saga-success.png`

## ❌  Rollback Flow (Failure Scenario)

> Represents the compensating flow when a failure occurs at any step in the process, forcing the orchestrator to undo previous actions.

---
## Deployment Instructions

### :rocket: Installation

![](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)


```
git clone https://github.com/RamonBecker/ms-saga.git
```

![](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
```
git clone https://github.com/RamonBecker/ms-saga.git
or install github https://desktop.github.com/ 
```

## 🔨 Docker

Before cloning the project, you will need to install docker on your operating system.

For windows, enter the following from the link:

```
https://docs.docker.com/desktop/windows/install/
```

For linux, follow the procedure below:
- Update your existing list of packages:

```
sudo apt update
```

- Install some prerequisite packages that let apt use packages over HTTPS:

```
sudo apt install apt-transport-https ca-certificates curl software-properties-common

```

- Add the GPG key to the official Docker repository on your system:

```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```
- Add the Docker repository to the APT sources:

```
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"

```

- Update the package database with Docker packages from the newly added repository:

```
sudo apt update
```

- Make sure you are about to install from the Docker repository instead of the default Ubuntu repository:

```
apt-cache policy docker-ce
```

- Install Docker:

```
sudo apt install docker-ce
```

- Check if it is working:

```
sudo systemctl status docker
```

- Once you have completed the docker installation, go to the infrastructure folder and run the following commands

```
docker compose up --build
docker compose up -d
```


## 🔄 Features Implemented

- Asynchronous processing of **distributed transactions**
- Execution and orchestration of **sagas with rollback**
- Event-based communication using **Apache Kafka**
- Distributed data storage with PostgreSQL and MongoDB
- Docker containers for running the architecture locally
- Use of unique identifiers and **idempotency control** to avoid inconsistencies

---

## 📣 Apache Kafka

**Apache Kafka** is a distributed event streaming platform used for **asynchronous communication between microservices**. In this project, Kafka acts as the **message broker** responsible for managing the events that trigger each stage of the Orchestrated Saga.

Kafka is used to:

- Publish events across services involved in the saga
- Decouple producers and consumers
- Enable reactivity and scalability in the orchestration
- Control rollback flows in failure scenarios

Kafka provides **high performance**, **fault tolerance**, **partitioning**, and **event persistence**, making it ideal for event-driven architectures.




---

## 📌 Adopted Patterns

* **Orchestrated Saga**: centralized transaction coordination
* **Event-Driven Architecture (EDA)**: asynchronous service communication
* **Outbox Pattern** (planned): persistent and reliable event storage
* **Idempotency**: ensures operations are not duplicated

---

## 📨 Example: Order API Payload & Response

To better understand how the system works in practice, here is an example of a request and response for creating a new order via the Order API.

### ✅ Request Payload

```json
{
  "products": [
    {
      "product": {
        "code": "COMIC_BOOKS",
        "unitValue": 15.50
      },
      "quantity": 3
    },
    {
      "product": {
        "code": "BOOKS",
        "unitValue": 9.90
      },
      "quantity": 1
    }
  ]
}
```

### 📬 Response Example

```json
{
  "id": "64429e987a8b646915b3735f",
  "products": [
    {
      "product": {
        "code": "COMIC_BOOKS",
        "unitValue": 15.5
      },
      "quantity": 3
    },
    {
      "product": {
        "code": "BOOKS",
        "unitValue": 9.9
      },
      "quantity": 1
    }
  ],
  "createdAt": "2025-04-21T14:32:56.335943085",
  "transactionId": "1682087576536_99d2ca6c-f074-41a6-92e0-21700148b519"
}
```

This response includes:

* `id`: Unique identifier of the order stored in MongoDB.
* `createdAt`: Timestamp when the order was created.
* `transactionId`: Identifier used to track the distributed transaction across services (helps with idempotency and rollback).

This response confirms the order has been successfully created and that the orchestrator will manage the subsequent steps in the saga:

1. Validate and process payment
2. Reserve or update inventory
3. Finalize or rollback depending on success or failure

---

## 🚪 Application Ports

The applications run on the following ports:

* `Order-Service`: 3000
* `Orchestrator-Service`: 8080
* `Product-Validation-Service`: 8090
* `Payment-Service`: 8091
* `Inventory-Service`: 8092
* `Apache Kafka`: 9092
* `Redpanda Console`: 8081
* `PostgreSQL (Product-DB)`: 5432
* `PostgreSQL (Payment-DB)`: 5433
* `PostgreSQL (Inventory-DB)`: 5434
* `MongoDB (Order-DB)`: 27017

---

## 🔍 Saga Inspection Endpoint

It is possible to retrieve saga data using either the `orderId` or the `transactionId`. Both queries return the same result.

### Example:

```http
GET http://localhost:3000/api/event?orderId=64429e987a8b646915b3735f
GET http://localhost:3000/api/event?transactionId=1682087576536_99d2ca6c-f074-41a6-92e0-21700148b519
```

---
## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!



[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40' width='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40' width='40'>](https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)
