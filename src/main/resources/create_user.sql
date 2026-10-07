-- Créer un utilisateur l'application rentals

CREATE USER 'rental_user'@'localhost' IDENTIFIED BY 'un_mot_de_passe';
GRANT SELECT, INSERT, UPDATE, DELETE ON rentals.* TO 'rental_user'@'localhost';
FLUSH PRIVILEGES;