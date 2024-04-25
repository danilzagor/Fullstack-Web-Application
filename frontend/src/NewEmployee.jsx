import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import styles from "./NewEmployee.module.css";
const NewEmployee = () => {
  const [employee, setEmployee] = useState({
    name: '',
    surname: '',
    email: '',
    dateOfBirth: '',
    dateOfHiring: '',
    dateOfFiring: '',
    multipartFile: null
  });
  const navigation = useNavigate();
  const [isPending, setIsPending] = useState(false);
  const handleChange = (e) => {
    if (e.target.name === 'multipartFile') {
      setEmployee({ ...employee, multipartFile: e.target.files[0] });
    } else {
      const { name, value } = e.target;
      setEmployee({ ...employee, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append('image', employee.multipartFile);
      formData.append('name', employee.name);
      formData.append('surname', employee.surname);
      formData.append('email', employee.email);
      formData.append('dateOfBirth', employee.dateOfBirth);
      formData.append('dateOfHiring', employee.dateOfHiring);
      formData.append('dateOfFiring', employee.dateOfFiring);

      await fetch('http://localhost:8080/api/v1/employee', {
        method: 'POST',
        body: formData
      });
      setIsPending(false);
      navigation('/list');
    } catch (error) {
      setIsPending(false);
      {error && <div><h1>ERROR</h1></div>}
    }
  };

  return (
     <form onSubmit={handleSubmit}>

          <div className={styles.formGroup}>
            <input type="text" className={styles.formControl} name="name" value={employee.name} onChange={handleChange} placeholder="Name" required />
          </div>
          <div className={styles.formGroup}>
            <input type="text" className={styles.formControl} name="surname" value={employee.surname} onChange={handleChange} placeholder="Surname" required />
          </div>
          <div className={styles.formGroup}>
            <input type="email" className={styles.formControl} name="email" value={employee.email} onChange={handleChange} placeholder="Email" required />
          </div>
          <div className={styles.formGroup}>
            <p className={styles.formLabel}>Date of Birth</p>
            <input type="date" className={styles.formControl} name="dateOfBirth" value={employee.dateOfBirth} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <p className={styles.formLabel}>Date of Hiring</p>
            <input type="date" className={styles.formControl} name="dateOfHiring" value={employee.dateOfHiring} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <p className={styles.formLabel}>Date of Firing</p>
            <input type="date" className={styles.formControl} name="dateOfFiring" value={employee.dateOfFiring} onChange={handleChange} />
          </div>
          <div className={styles.formGroup}>
                      <label htmlFor="multipartFile" className={styles.formLabel}>Upload photo of an employee</label>
                      <input type="file" className={styles.formControl} id="multipartFile" name="multipartFile" accept="image/*" onChange={handleChange} />
          </div>
          {!isPending && <button type="submit" className={`${styles.btn} ${styles.btnPrimary}`}>Add a new person</button>}
          {isPending && <button disabled type="submit" className={`${styles.btn} ${styles.btnPrimary}`}>Adding is in progress</button>}
        </form>
  );
};

export default NewEmployee;