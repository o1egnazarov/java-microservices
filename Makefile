.PHONY: dev cloud clean

# Common variables
SHELL := /bin/bash
MAVEN := mvn -T 1C

# Service directories
GATEWAY_DIR := gateway-service
CURRENCY_RATE_DIR := currency-rate
PROCESSING_DIR := processing
DISCOVERY_DIR := discovery-service

# Development mode (default)
dev: 
	@echo "Starting services in development mode..."
	@echo "Starting Gateway Service..." && cd $(GATEWAY_DIR) && $(MAVEN) spring-boot:run & \
	echo "Starting Currency Rate Service..." && sleep 5 && cd $(CURRENCY_RATE_DIR) && $(MAVEN) spring-boot:run & \
	echo "Starting Processing Service..." && sleep 5 && cd $(PROCESSING_DIR) && $(MAVEN) spring-boot:run

# Cloud mode
cloud:
	@echo "Starting services in cloud mode..."
	@echo "Starting Discovery Service..." && cd $(DISCOVERY_DIR) && $(MAVEN) spring-boot:run -Dspring-boot.run.profiles=cloud & \
	echo "Starting Gateway Service..." && sleep 10 && cd $(GATEWAY_DIR) && $(MAVEN) spring-boot:run -Dspring-boot.run.profiles=cloud & \
	echo "Starting Currency Rate Service..." && sleep 5 && cd $(CURRENCY_RATE_DIR) && $(MAVEN) spring-boot:run -Dspring-boot.run.profiles=cloud & \
	echo "Starting Processing Service..." && sleep 5 && cd $(PROCESSING_DIR) && $(MAVEN) spring-boot:run -Dspring-boot.run.profiles=cloud

# Clean all services
clean:
	@echo "Cleaning all services..."
	@cd $(GATEWAY_DIR) && $(MAVEN) clean
	@cd $(CURRENCY_RATE_DIR) && $(MAVEN) clean
	@cd $(PROCESSING_DIR) && $(MAVEN) clean
	@cd $(DISCOVERY_DIR) && $(MAVEN) clean

# Help
default: help
help:
	@echo "Available targets:"
	@echo "  dev     - Start services in development mode (gateway, currency-rate, processing)"
	@echo "  cloud   - Start services in cloud mode (discovery, gateway, currency-rate, processing) with cloud profile"
	@echo "  clean   - Clean all services"
	@echo "  help    - Show this help message"
