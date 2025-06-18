import React, { useEffect, useState } from "react";
import axios from "axios";

function ProfilePage() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token");

    axios
      .get("http://localhost:8080/api/profile", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setMessage(res.data);
      })
      .catch((err) => {
        setMessage("Yetkisiz erişim.");
      });
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Profil Sayfası</h2>
      <p>{message}</p>
    </div>
  );
}

export default ProfilePage;
