import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import styles from './NavBar.module.css';
const NavBar = () => {
    return ( 
       <div className={styles.navigationBar}>
        <h1>Company</h1>
        <div className={styles.links}>
          <Link to="/" >Home</Link>
          <Link to="/list">Employees</Link>
        </div>
       </div>
     );
}
 
export default NavBar;