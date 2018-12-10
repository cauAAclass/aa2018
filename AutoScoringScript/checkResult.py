
TestFile =   {1:'TestFile1.csv', 2:'TestFile2.csv', 3:'TestFile3.csv'}
TestAnswerFile = {1:'__result_ospp1.txt', 2:'__result_ospp2.txt', 3:'__result_ospp3.txt'}

# Testing Script 정리
import glob
import os

Error_Complie = []
Error_Result = []
Pass_Student = []

for JavaFileName in glob.glob("*.java"):
    FileName = JavaFileName.split('.')[0]
    FileInfo = JavaFileName.replace('ospp','')
    FileInfo = FileInfo.replace('.','_')
    FileInfo = FileInfo.split('_')

    projectNum = int(FileInfo[0])
    StudentID = FileInfo[1]

    print('\n *', str(StudentID), "ㅡ"*20)

    #print(FileInfo)
    ComplieCmd = 'javac '+JavaFileName
    JavaClassFileName = FileName+'.class'
    TestResultFile = str(projectNum)+'_'+str(StudentID)+'.txt'

    JavaCallCmd = 'java '+FileName +' '+ TestFile[projectNum]+' '+TestResultFile
    
    #Complie Script
    print(">"*3,ComplieCmd)
    os.system(ComplieCmd)
    if len(glob.glob(JavaClassFileName))==0:
        print("\nComplie Error!\n")
        Error_Complie.append(StudentID)
        continue
    
    print(">"*3,'java '+JavaCallCmd)
    os.system(JavaCallCmd)
    if len(glob.glob(TestResultFile))==0:
        print("\nCode Error!\n")
        Error_Complie.append(StudentID)
        os.remove(FileName+'.class')
        continue
    
    with open(TestAnswerFile[projectNum], 'r') as File:
        RealAns = File.readlines()    
    with open(TestResultFile, 'r') as File:
        UserAns = File.readlines()
    
    if RealAns[0] != UserAns[0]:
        print("\nResult Error!\n")
        Error_Result.append(StudentID)
        os.remove(FileName+'.class')
        continue

    print("\nPass!\n")
    Pass_Student.append(StudentID)
    os.remove(FileName+'.class')

print("\nComplie Error or Code Error: ",Error_Complie)
print("\nResult Error: ",Error_Result)
print("\nPass! ",Pass_Student)
