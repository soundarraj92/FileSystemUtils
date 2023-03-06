# FileSystemUtils

Contains few endpoints for files management inside your drive.

URL wise details are as below:
-----------------------------

API: /ezio/api/properties
MethodType: POST
Desc: Gives a high level view of files and folders with their sizes in input directory

API: /ezio/api/filelist
MethodType: POST
Desc: Traverses inside folders and gives a complete list of all files with full paths

API: /ezio/api/duplicate-list
MethodType: POST
Desc: Acquires complete list of all files and checks if there are matching files w.r.t filename and shows both duplicate and original, Ignores the filetype if specified

API: /ezio/api/search
MethodType: POST
Desc: TBD

API: /ezio/api/size-zero
MethodType: POST
Desc: Gets all files list and returns the files that are of size zero

Models:
------
Common Input Model for all above APIs:

{
 "fsPath":"H:\\Temp", // Input Directory
 "fileTypeExclusions":".jpg", // Applicable in duplicate-list API
 "detailedInfo":true // No function as of now
}