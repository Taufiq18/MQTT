# Use CentOS 8 as base image
FROM centos:8

# Install necessary tools and Mosquitto
RUN yum -y update && \
    yum -y install epel-release && \
    yum -y install mosquitto java-11-openjdk-devel && \
    yum clean all

# Create a non-root user
RUN useradd -ms /bin/bash appuser

# Set working directory to /opt and change ownership
WORKDIR /opt
RUN chown -R appuser:appuser /opt

# Copy application files to /opt
COPY --chown=appuser:appuser . .

# Switch to non-root user
USER appuser

# Expose necessary ports
EXPOSE 8080 1883

# Start Mosquitto broker and Spring Boot application
CMD ["sh", "-c", "mosquitto -c /etc/mosquitto/mosquitto.conf & java -jar /opt/demo-0.0.1-SNAPSHOT.jar"]
