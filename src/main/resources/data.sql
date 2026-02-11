INSERT INTO Role (name) VALUES
('STUDENT'),
('ADMIN'),
('LECTURER');

INSERT INTO Student (
    first_name, last_name, index_number, email,
    password_hash, account_status, registration_date, role_id
) VALUES
('John', 'Smith', 'S12345', 'john.smith@uni.edu', 'hash123', 'ACTIVE', '2024-10-01',
 (SELECT role_id FROM Role WHERE name = 'STUDENT')),

('Anna', 'Kowalska', 'S12346', 'anna.kowalska@uni.edu', 'hash456', 'ACTIVE', '2024-10-02',
 (SELECT role_id FROM Role WHERE name = 'STUDENT')),

('Mark', 'Brown', 'A00001', 'admin@uni.edu', 'adminhash', 'ACTIVE', '2024-01-01',
 (SELECT role_id FROM Role WHERE name = 'ADMIN'));

INSERT INTO Course (name, description, ects, hours, semester, capacity) VALUES
('Databases', 'Relational databases and SQL', 6, 60, 3, 30),
('Algorithms', 'Algorithms and data structures', 5, 45, 2, 25),
('Operating Systems', 'Processes, threads, memory', 6, 60, 4, 20);

INSERT INTO Enrollment (enrollment_date, status, student_id, course_id) VALUES
('2025-02-01', 'ENROLLED',
 (SELECT student_id FROM Student WHERE index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Databases')),

('2025-02-01', 'ENROLLED',
 (SELECT student_id FROM Student WHERE index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Algorithms')),

('2025-02-02', 'ENROLLED',
 (SELECT student_id FROM Student WHERE index_number = 'S12346'),
 (SELECT course_id FROM Course WHERE name = 'Databases'));

INSERT INTO Grade (value, type, issue_date, student_id, course_id) VALUES
(4.5, 'EXAM', '2025-06-15',
 (SELECT student_id FROM Student WHERE index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Databases')),

(5.0, 'PROJECT', '2025-06-20',
 (SELECT student_id FROM Student WHERE index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Algorithms')),

(3.5, 'EXAM', '2025-06-18',
 (SELECT student_id FROM Student WHERE index_number = 'S12346'),
 (SELECT course_id FROM Course WHERE name = 'Databases'));

INSERT INTO Schedule (semester, student_id) VALUES
(3, (SELECT student_id FROM Student WHERE index_number = 'S12345')),
(3, (SELECT student_id FROM Student WHERE index_number = 'S12346'));

INSERT INTO ClassSession (
    day_of_week, start_time, end_time, room, form, schedule_id, course_id
) VALUES
('MONDAY', '08:00:00', '09:30:00', 'B101', 'LECTURE',
 (SELECT schedule_id FROM Schedule s
  JOIN Student st ON s.student_id = st.student_id
  WHERE st.index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Databases')),

('WEDNESDAY', '10:00:00', '11:30:00', 'C202', 'LAB',
 (SELECT schedule_id FROM Schedule s
  JOIN Student st ON s.student_id = st.student_id
  WHERE st.index_number = 'S12345'),
 (SELECT course_id FROM Course WHERE name = 'Algorithms')),

('TUESDAY', '12:00:00', '13:30:00', 'A303', 'LECTURE',
 (SELECT schedule_id FROM Schedule s
  JOIN Student st ON s.student_id = st.student_id
  WHERE st.index_number = 'S12346'),
 (SELECT course_id FROM Course WHERE name = 'Databases'));

INSERT INTO Notification (content, sent_at, type, status, student_id) VALUES
('Enrollment confirmed for Databases',
 '2025-02-01 10:00:00', 'INFO', 'SENT',
 (SELECT student_id FROM Student WHERE index_number = 'S12345')),

('New grade published',
 '2025-06-20 15:30:00', 'GRADE', 'SENT',
 (SELECT student_id FROM Student WHERE index_number = 'S12345')),

('Payment deadline approaching',
 '2025-03-01 09:00:00', 'PAYMENT', 'SENT',
 (SELECT student_id FROM Student WHERE index_number = 'S12346'));

INSERT INTO Payment (amount, due_date, status, payment_date, student_id) VALUES
(1200.00, '2025-03-15', 'PAID', '2025-03-10',
 (SELECT student_id FROM Student WHERE index_number = 'S12345')),

(1200.00, '2025-03-15', 'PENDING', NULL,
 (SELECT student_id FROM Student WHERE index_number = 'S12346'));

INSERT INTO Loan (book_title, loan_date, return_deadline, student_id) VALUES
('Database System Concepts', '2025-02-10', '2025-03-10',
 (SELECT student_id FROM Student WHERE index_number = 'S12345')),

('Introduction to Algorithms', '2025-02-12', '2025-03-12',
 (SELECT student_id FROM Student WHERE index_number = 'S12346'));
