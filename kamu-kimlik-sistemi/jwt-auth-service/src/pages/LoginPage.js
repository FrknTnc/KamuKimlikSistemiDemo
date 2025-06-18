import React from "react";
import LoginForm from "../components/LoginForm"; // veya doğru path neyse
// Eğer LoginForm aynı klasörde değilse "../components/LoginForm" gibi path'i düzelt

function LoginPage() {
  return (
    <div style={{ padding: "2rem" }}>
      <LoginForm />
    </div>
  );
}

export default LoginPage;
