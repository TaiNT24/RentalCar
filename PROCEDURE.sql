IF OBJECT_ID('sp_GetAvailableQuantity', 'P') IS NOT NULL
	DROP PROCEDURE sp_GetAvailableQuantity
GO

CREATE PROCEDURE sp_GetAvailableQuantity @IDCar INT, @DateWantRent VARCHAR(50), @DateWantReturn VARCHAR(50)
	AS
	BEGIN
		DECLARE @totalAvailableQuantity INT, @rowRenting int;

		SELECT @totalAvailableQuantity = 0;
		
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

			END
		

		SELECT @totalAvailableQuantity = @totalAvailableQuantity + (SELECT AvailableQuantity 
																	FROM dbo.Car 
																	WHERE IDCar=@IDCar)


		SELECT @totalAvailableQuantity AS AvailableQuantity;
	END
GO
------
EXEC sp_GetAvailableQuantity 1, '02/26/2019', '02/28/2019'

