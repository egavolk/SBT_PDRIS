#!/bin/bash

minikube delete;

minikube start --vm=true --driver=hyperkit;

kubectl config use-context minikube;

minikube addons enable ingress;

eval $(minikube docker-env);

mvn clean package -f ..;

kubectl apply -f db;

sleep 30s;

kubectl apply -f services;