IF OBJECT_ID('sp_GetAvailableQuantity', 'P') IS NOT NULL
	DROP PROCEDURE sp_GetAvailableQuantity
GO

CREATE PROCEDURE sp_GetAvailableQuantity @IDCar INT, @DateWantRent VARCHAR(50), @DateWantReturn VARCHAR(50)
	AS
	BEGIN
		DECLARE @totalAvailableQuantity INT, @rowRenting int;
		
		SELECT @rowRenting = (SELECT COUNT(IDCar) FROM dbo.RentCar WHERE IDCar = @IDCar AND Status = 'Paymented')

		IF(@rowRenting > 0)
			BEGIN
				SELECT @totalAvailableQuantity = (	SELECT SUM(Quantity) 
													FROM dbo.RentCar
													WHERE IDRent IN (SELECT IDRent 
																	FROM dbo.DateTimeRentCar
																	WHERE DateRent > @DateWantReturn 
																		OR DateReturn < @DateWantRent
																	)
														AND IDCar = @IDCar
														AND Status = 'Paymented'
												)
				IF(@totalAvailableQuantity IS NULL)
					BEGIN
						SELECT @totalAvailableQuantity = 0;
					END
			END
			ELSE
				BEGIN
				    SELECT @totalAvailableQuantity = 0;
				END
		

		SELECT @totalAvailableQuantity = @totalAvailableQuantity + (SELECT AvailableQuantity 
																	FROM dbo.Car 
																	WHERE IDCar=@IDCar)


		SELECT @totalAvailableQuantity AS AvailableQuantity;
	END
GO
------
EXEC sp_GetAvailableQuantity 1, '03/08/2020', '03/20/2020'

