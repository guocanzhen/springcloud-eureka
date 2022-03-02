CREATE TABLE `dept` (
                        `dept_no` bigint NOT NULL AUTO_INCREMENT,
                        `dept_name` varchar(255) DEFAULT NULL,
                        `db_source` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;