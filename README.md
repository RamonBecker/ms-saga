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
## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!



[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40'>](https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)
