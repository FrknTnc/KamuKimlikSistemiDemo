import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./css/LoginForm.css";

function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password,
      });

      const token = response.data.replace("Bearer ", "");
      localStorage.setItem("token", token);
      alert("Giriş başarılı!");
    } catch (err) {
        console.error("Giriş hatası:", err);

        // 🔽 Sunucudan gelen özel hata mesajını göster
        if (err.response && err.response.data) {
            setError(err.response.data); // örn: "Çok fazla hatalı giriş yapıldı..."
        } else {
            setError("Sunucu hatası oluştu.");
        }
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleLogin}>
        <h2>JWT Login</h2>
        <input
          type="text"
          placeholder="Kullanıcı Adı"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Şifre"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Giriş Yap</button>
        {error && <p className="error">{error}</p>}

        <p className="register-link">
          Hesabın yok mu? <Link to="/register">Kayıt Ol</Link>
        </p>
      </form>
    </div>
  );
}

export default LoginForm;
