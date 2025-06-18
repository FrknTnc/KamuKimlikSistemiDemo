import { useState } from 'react';
import { register } from '../api/auth';
import './css/RegisterForm.css'; // stil dosyasını import et

export default function RegisterForm() {
  const [form, setForm] = useState({ username: '', password: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await register(form);
      alert(res.data);
    } catch (err) {
      alert('Kayıt başarısız');
    }
  };

  return (
    <div className="container">
      <form className="form" onSubmit={handleSubmit}>
        <h2>JWT Register</h2>
        <input
          type="text"
          placeholder="Kullanıcı Adı"
          value={form.username}
          onChange={(e) => setForm({ ...form, username: e.target.value })}
        />
        <input
          type="password"
          placeholder="Şifre"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        />
        <button className="green-button" type="submit">Kayıt Ol</button>
      </form>
    </div>
  );
}
