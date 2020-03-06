--Trigger when Status = 'Paymented' => renting
IF OBJECT_ID('trg_AvailableQuantity', 'TR') IS NOT NULL
	DROP trigger trg_AvailableQuantity
GO
CREATE TRIGGER trg_AvailableQuantity ON dbo.RentCar AFTER UPDATE AS
BEGIN
--
	DECLARE @QuantityRent INT, @StatusRent VARCHAR(50)
	
	SELECT @StatusRent = (SELECT Status FROM Inserted)
							
	
	IF(@StatusRent = 'Paymented')
		BEGIN
			SELECT @QuantityRent = (SELECT SUM(Quantity)
							FROM dbo.RentCar
							WHERE Status='Paymented' 
								AND IDCar = (SELECT IDCar FROM Inserted)
							)

			IF(@QuantityRent > 0)
				BEGIN
					UPDATE dbo.Car SET AvailableQuantity = Car.Quantity - @QuantityRent
							WHERE Car.IDCar = (SELECT IDCar FROM Inserted)
			
				END
			
		END
END
GO
------------------------------------------------------------
------------------------------------------------------------
--Trigger when Status = 'Returned' => return the rental car
IF OBJECT_ID('trg_ReturnQuantity', 'TR') IS NOT NULL
	DROP trigger trg_ReturnQuantity
GO
CREATE TRIGGER trg_ReturnQuantity ON dbo.RentCar AFTER UPDATE AS
BEGIN

	DECLARE @QuantityReturn INT, @StatusRent VARCHAR(50)
	
	SELECT @StatusRent = (SELECT Status FROM Inserted)
							
	
	IF(@StatusRent = 'Returned')
		BEGIN
			SELECT @QuantityReturn = (SELECT Inserted.Quantity FROM Inserted)

			IF(@QuantityReturn > 0)
				BEGIN
					UPDATE dbo.Car 
					SET AvailableQuantity = Car.AvailableQuantity + @QuantityReturn
					WHERE Car.IDCar = (SELECT IDCar FROM Inserted)

				END
			
		END
END
GO

