import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080/api', // <== bu port, `minikube service auth-service` çıktısından alındı
});


export const login = (credentials) => API.post('/auth/login', credentials);
export const register = (credentials) => API.post('/auth/register', credentials);
